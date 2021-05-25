import React from 'react'
import {
  FacebookShareButton,
  LinkedinShareButton,
  RedditShareButton,
  TelegramShareButton,
  TwitterShareButton,
  FacebookIcon,
  LinkedinIcon,
  TelegramIcon,
  TwitterIcon,
  RedditIcon,
} from 'react-share'
import { CopyToClipboard } from 'react-copy-to-clipboard'
import { FaCopy } from 'react-icons/fa'

const ShareIcons = ({ url }) => {
  const ICON_SIZE = 32
  const iconBgColor = '#4B68F1'

  const iconBgStyle = {
    fill: iconBgColor,
  }

  const TITLE = 'Check out this NFT Auction! '

  return (
    <div className='flex justify-between'>
      <FacebookShareButton url={url} title={TITLE}>
        <FacebookIcon round size={ICON_SIZE} bgStyle={iconBgStyle} />
      </FacebookShareButton>
      <LinkedinShareButton url={url} title={TITLE}>
        <LinkedinIcon round size={ICON_SIZE} bgStyle={iconBgStyle} />
      </LinkedinShareButton>
      <TelegramShareButton url={url} title={TITLE}>
        <TelegramIcon round size={ICON_SIZE} bgStyle={iconBgStyle} />
      </TelegramShareButton>
      <TwitterShareButton url={url} title={TITLE}>
        <TwitterIcon round size={ICON_SIZE} bgStyle={iconBgStyle} />
      </TwitterShareButton>
      <RedditShareButton url={url} title={TITLE}>
        <RedditIcon round size={ICON_SIZE} bgStyle={iconBgStyle} />
      </RedditShareButton>
    </div>
  )
}

const Modal = ({ isOpen, close, auction }) => {
  const [hasCopiedText, setCopiedTextStatus] = React.useState(false)
  if (!isOpen) return null

  const shareUrl = window.location.href

  const handleCopy = () => {
    setCopiedTextStatus(true)
    setTimeout(() => {
      setCopiedTextStatus(false)
    }, 1500)
  }

  return (
    <div className='fixed z-50 inset-0 overflow-y-none flex justify-center items-center'>
      <div className='pb-alot flex items-end justify-center pt-4 px-4 text-center'>
        <div
          className='fixed inset-0 transition-opacity'
          aria-hidden='true'
          style={{ touchAction: 'none' }}
          onClick={close}
        >
          <div className='absolute inset-0 bg-gray-500 opacity-75'></div>
        </div>
        <div
          className='w-full inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full'
          role='dialog'
          aria-modal='true'
          aria-labelledby='modal-headline'
        >
          <div className='bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4 w-full'>
            {hasCopiedText && (
              <p className='text-black mb-2 absolute left-2 top-2'>Copied!</p>
            )}
            <div className='text-center'>
              <CopyToClipboard onCopy={handleCopy} text={shareUrl}>
                <FaCopy
                  className='absolute right-2 top-2 cursor-pointer'
                  style={{ color: '#4B68F1' }}
                />
              </CopyToClipboard>
              <p className='text-black m-3 py-3'>{shareUrl}</p>
            </div>
            <ShareIcons url={shareUrl} />
          </div>
        </div>
      </div>
    </div>
  )
}

export default Modal
